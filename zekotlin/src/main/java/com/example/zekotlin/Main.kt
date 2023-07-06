package com.example.zekotlin

enum class StatusType {
    SUCCESS,
    ERROR,
    EMPTY,
    UNKNOWN
}

data class Item(val item: String)

sealed interface ViewAction {
    object GetList: ViewAction
    data class SelectedItem(val item: Item): ViewAction
    data class DeleteItem(val item: Item): ViewAction
    object RefreshList: ViewAction
}

val numberSelected = 200

fun main(args: Array<String>) {
    val color = getColor(StatusType.UNKNOWN)
    println(color)

    val action = ViewAction.SelectedItem(Item("any"))
    handleViewAction(action)

    val actionGet = ViewAction.GetList
    handleViewAction(actionGet)

    getNumber()
}

private fun getNumber() {
    when(numberSelected) {
       in 0..100 -> println("0 ~ 100")
       in  200 .. 400 -> println("200 ~ 400")
       else -> println("> 400")
    }

    when {
       numberSelected > 400 -> println("number select > 400")
       else -> println("number select < 400")
    }
}

private fun handleViewAction(viewAction: ViewAction) {
    when(viewAction) {
        is ViewAction.GetList -> println("get list")
        is ViewAction.RefreshList -> println("get refresh list")
        is ViewAction.SelectedItem -> println("item selected ${viewAction.item}")
        is ViewAction.DeleteItem -> println("item to delete ${viewAction.item}")
    }
}

private fun getColor(statusType: StatusType): String {
    return when(statusType) {
        StatusType.SUCCESS -> "blue"
        StatusType.ERROR -> "red"
        StatusType.UNKNOWN, StatusType.EMPTY -> "orange"
    }
}