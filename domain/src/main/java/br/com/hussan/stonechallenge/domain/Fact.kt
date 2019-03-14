package br.com.hussan.stonechallenge.domain

data class Fact(var id: String, val value: String, val category: List<String>? = null, var query: String = "")
