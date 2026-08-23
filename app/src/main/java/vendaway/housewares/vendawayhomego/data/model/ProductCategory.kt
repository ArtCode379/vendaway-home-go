package vendaway.housewares.vendawayhomego.data.model

import androidx.annotation.StringRes
import vendaway.housewares.vendawayhomego.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    KITCHEN(R.string.category_kitchen),
    ELECTRONICS(R.string.category_electronics),
    HOME(R.string.category_home),
    SEASONAL(R.string.category_seasonal),
}
