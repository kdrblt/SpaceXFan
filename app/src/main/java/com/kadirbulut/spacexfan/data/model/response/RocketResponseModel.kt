package com.kadirbulut.spacexfan.data.model.response

import com.google.gson.annotations.SerializedName

// rocket response model
data class RocketResponseModel(
    @SerializedName("height") var height: Height?,
    @SerializedName("diameter") var diameter: Height?,
    @SerializedName("mass") var mass: Height,
    @SerializedName("first_stage") var firstStage: FirstStage?,
    @SerializedName("second_stage") var secondStage: SecondStage?,
    @SerializedName("engines") var engines: Engines?,
    @SerializedName("landing_legs") var landingLegs: LandingLegs?,
    @SerializedName("payload_weights") var payloadWeights: List<PayloadWeights>,
    @SerializedName("flickr_images") var flickrImages: List<String>,
    @SerializedName("name") var name: String?,
    @SerializedName("type") var type: String?,
    @SerializedName("active") var active: Boolean?,
    @SerializedName("stages") var stages: Int?,
    @SerializedName("boosters") var boosters: Int?,
    @SerializedName("cost_per_launch") var costPerLaunch: Int?,
    @SerializedName("success_rate_pct") var successRatePct: Int?,
    @SerializedName("first_flight") var firstFlight: String?,
    @SerializedName("country") var country: String?,
    @SerializedName("company") var company: String?,
    @SerializedName("wikipedia") var wikipedia: String?,
    @SerializedName("description") var description: String?,
    @SerializedName("id") var id: String?

) {
    data class Height(
        @SerializedName("meters") var meters: Int?,
        @SerializedName("feet") var feet: Double?
    )

    data class FirstStage(
        @SerializedName("thrust_sea_level") var thrustSeaLevel: ThrustSeaLevel?,
        @SerializedName("thrust_vacuum") var thrustVacuum: ThrustVacuum?,
        @SerializedName("reusable") var reusable: Boolean?,
        @SerializedName("engines") var engines: Int?,
        @SerializedName("fuel_amount_tons") var fuelAmountTons: Int?,
        @SerializedName("burn_time_sec") var burnTimeSec: Int?
    )

    data class ThrustSeaLevel(
        @SerializedName("kN") var kN: Int?,
        @SerializedName("lbf") var lbf: Int?
    )

    data class ThrustVacuum(
        @SerializedName("kN") var kN: Int?,
        @SerializedName("lbf") var lbf: Int?
    )

    data class SecondStage(
        @SerializedName("thrust") var thrust: Thrust?,
        @SerializedName("payloads") var payloads: Payloads?,
        @SerializedName("reusable") var reusable: Boolean? = null,
        @SerializedName("engines") var engines: Int? = null,
        @SerializedName("fuel_amount_tons") var fuelAmountTons: Int? = null,
        @SerializedName("burn_time_sec") var burnTimeSec: Int? = null
    )

    data class Payloads(
        @SerializedName("composite_fairing") var compositeFairing: CompositeFairing?,
        @SerializedName("option_1") var option1: String? = null
    )

    data class CompositeFairing(
        @SerializedName("height") var height: Height?,
        @SerializedName("diameter") var diameter: Diameter?
    )

    data class Thrust(
        @SerializedName("kN") var kN: Int? = null,
        @SerializedName("lbf") var lbf: Int? = null
    )

    data class Diameter(
        @SerializedName("meters") var meters: Double? = null,
        @SerializedName("feet") var feet: Double? = null
    )

    data class Engines(
        @SerializedName("isp") var isp: Isp?,
        @SerializedName("thrust_sea_level") var thrustSeaLevel: ThrustSeaLevel?,
        @SerializedName("thrust_vacuum") var thrustVacuum: ThrustVacuum?,
        @SerializedName("number") var number: Int? = null,
        @SerializedName("type") var type: String? = null,
        @SerializedName("version") var version: String? = null,
        @SerializedName("layout") var layout: String? = null,
        @SerializedName("engine_loss_max") var engineLossMax: Int? = null,
        @SerializedName("propellant_1") var propellant1: String? = null,
        @SerializedName("propellant_2") var propellant2: String? = null,
        @SerializedName("thrust_to_weight") var thrustToWeight: Double? = null
    )

    data class LandingLegs(
        @SerializedName("number") var number: Int? = null,
        @SerializedName("material") var material: String? = null
    )

    data class PayloadWeights(
        @SerializedName("id") var id: String? = null,
        @SerializedName("name") var name: String? = null,
        @SerializedName("kg") var kg: Int? = null,
        @SerializedName("lb") var lb: Int? = null
    )

    data class Isp(
        @SerializedName("sea_level") var seaLevel: Int? = null,
        @SerializedName("vacuum") var vacuum: Int? = null
    )
}
