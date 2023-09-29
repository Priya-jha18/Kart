import com.google.gson.annotations.SerializedName

data class HomeResponseModel(
    val status: Int,
    val message: String,
    val result: Result
)

data class Result(
    val bannerOne: List<BannerOne>,
    @SerializedName("instant_services")
    val instantServices: List<InstantService>
)

data class BannerOne(
    @SerializedName("ImageUrl")
    val imageUrl: List<String>,
    @SerializedName("__v")
    val v: Long
)

data class InstantService(
    val categoryName: String,
    @SerializedName("__v")
    val v: Long
)
