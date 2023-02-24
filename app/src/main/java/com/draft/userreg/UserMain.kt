package com.draft.userreg

data class UserMain(
        val name: String,
        val age: Int,
        val gender: Gender,
        val foodChoice: FoodChoice,
        val dateOfBirth: String,
        val isMarried: Boolean
    )

enum class Gender {
    MALE,
    FEMALE,
    OTHER
}

enum class FoodChoice {
    VEGETARIAN,
    NON_VEG,
    VEGAN
}
