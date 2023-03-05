package ru.veronikarepina.giphy.data.models

data class Giphy(
    val `data`: List<Data>,
    val meta: Meta,
    val pagination: Pagination
)

