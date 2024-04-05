package com.example.got.models

data class House(var url: String, var name: String, var region: String, var coatOfArms: String, var words: String,var titles: ArrayList<String>,
                 var seats: ArrayList<String>, var currentLord: String, var heir: String, var overlord: String, var founded: String, var founder: String,
                 var diedOut: String, var ancestralWeapons: ArrayList<String>, var cadetBranches: ArrayList<String>, var swornMembers: ArrayList<String>)

//data class House(var name: String, var region: String)