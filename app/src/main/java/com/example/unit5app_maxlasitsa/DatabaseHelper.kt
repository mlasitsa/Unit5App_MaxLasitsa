import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.unit5app_maxlasitsa.FoodItem

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_NAME (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_NAME TEXT," +
                "$COLUMN_CALORIES INTEGER," +
                "$COLUMN_DATE TEXT)"
        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addFoodEntry(foodEntry: FoodItem) {
        val db = this.writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAME, foodEntry.name)
            put(COLUMN_CALORIES, foodEntry.calories)
            put(COLUMN_DATE, foodEntry.date)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllFoodEntries(): List<FoodItem> {
        val foodEntries = mutableListOf<FoodItem>()
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)

        if (cursor.moveToFirst()) {
            do {
                val foodEntry = FoodItem(
                    cursor.getString(cursor.getColumnIndex(COLUMN_NAME)),
                    cursor.getInt(cursor.getColumnIndex(COLUMN_CALORIES)),
                    cursor.getString(cursor.getColumnIndex(COLUMN_DATE))
                )
                foodEntries.add(foodEntry)
            } while (cursor.moveToNext())
        }
        cursor.close()
        db.close()
        return foodEntries
    }

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "HealthApp.db"
        private const val TABLE_NAME = "food_entries"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAME = "name"
        private const val COLUMN_CALORIES = "calories"
        private const val COLUMN_DATE = "date"
    }
}