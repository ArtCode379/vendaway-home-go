package vendaway.housewares.vendawayhomego.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import vendaway.housewares.vendawayhomego.data.dao.CartItemDao
import vendaway.housewares.vendawayhomego.data.dao.OrderDao
import vendaway.housewares.vendawayhomego.data.database.converter.Converters
import vendaway.housewares.vendawayhomego.data.entity.CartItemEntity
import vendaway.housewares.vendawayhomego.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class OJFXTDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}