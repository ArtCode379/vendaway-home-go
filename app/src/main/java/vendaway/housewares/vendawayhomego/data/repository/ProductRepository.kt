package vendaway.housewares.vendawayhomego.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import vendaway.housewares.vendawayhomego.data.model.Product
import vendaway.housewares.vendawayhomego.data.model.ProductCategory

class ProductRepository {
    private val products = listOf(
        product(1, "Air fryer oven", "A roomy countertop oven with six cooking modes and an easy-clean basket.", ProductCategory.KITCHEN, 89.00, "photo-1585515320310-259814833e62"),
        product(2, "Cordless table lamp", "Rechargeable warm-light lamp with touch dimming for tables and patios.", ProductCategory.HOME, 39.00, "photo-1507473885765-e6ed057f782c"),
        product(3, "Compact coffee maker", "Brew rich filter coffee with a reusable filter and keep-warm mode.", ProductCategory.ELECTRONICS, 54.00, "photo-1517668808822-9ebb02f2a0e6"),
        product(4, "Textured throw blanket", "A soft woven throw in a natural shade with hand-knotted tassels.", ProductCategory.HOME, 32.00, "photo-1580301762395-21ce84d00bc6"),
        product(5, "Digital kitchen scale", "Precise measurements, tare function, and a slim easy-clean surface.", ProductCategory.KITCHEN, 24.00, "photo-1594223274512-ad4803739b7c"),
        product(6, "Portable Bluetooth speaker", "Room-filling sound in a compact splash-resistant design.", ProductCategory.ELECTRONICS, 49.00, "photo-1608043152269-423dbba4e7e1"),
        product(7, "Stoneware dinner set", "A twelve-piece service for four with a softly speckled glaze.", ProductCategory.KITCHEN, 68.00, "photo-1610701596007-11502861dcfa"),
        product(8, "Cooling tower fan", "Quiet three-speed airflow, sleep timer, and oscillation.", ProductCategory.SEASONAL, 72.00, "photo-1622480916113-9000ac49b79d"),
        product(9, "Aroma diffuser", "Ultrasonic mist and a gentle glow create an inviting atmosphere.", ProductCategory.HOME, 35.00, "photo-1602874801006-e26c8c9db6c1"),
        product(10, "Electric kettle", "Fast boiling with automatic shutoff and dry-boil protection.", ProductCategory.ELECTRONICS, 42.00, "photo-1594213114663-d94db9b17125"),
        product(11, "Picnic cooler basket", "An insulated basket that keeps food fresh on days out.", ProductCategory.SEASONAL, 46.00, "photo-1527761939622-933c787d76e2"),
        product(12, "Non-stick pan set", "Two durable pans with balanced soft-touch handles.", ProductCategory.KITCHEN, 58.00, "photo-1584990347449-a5d9f800a783"),
    )

    fun observeById(id: Int): Flow<Product?> = flowOf(products.find { it.id == id })

    fun getById(id: Int): Product? = products.find { it.id == id }

    fun observeAll(): Flow<List<Product>> = flowOf(products)

    private fun product(
        id: Int,
        title: String,
        description: String,
        category: ProductCategory,
        price: Double,
        imageId: String,
    ): Product {
        return Product(
            id = id,
            title = title,
            description = description,
            category = category,
            price = price,
            imageUrl = "https://images.unsplash.com/$imageId?w=1200",
        )
    }
}
