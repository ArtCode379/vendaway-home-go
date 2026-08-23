package vendaway.housewares.vendawayhomego.di

import androidx.room.Room
import vendaway.housewares.vendawayhomego.data.database.OJFXTDatabase
import org.koin.dsl.module

private const val DB_NAME = "ojfxt_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(
            context = get(),
            klass = OJFXTDatabase::class.java,
            name = DB_NAME
        ).build()
    }

    single { get<OJFXTDatabase>().cartItemDao() }

    single { get<OJFXTDatabase>().orderDao() }
}