package com.learn.rxjava3.android.examples.utils

import com.learn.rxjava3.android.examples.model.ApiUser
import com.learn.rxjava3.android.examples.model.User
import kotlin.collections.ArrayList

object Utils {

    fun getApiUserList(): List<ApiUser> {

        val apiUserList = ArrayList<ApiUser>()

        val apiUserOne = ApiUser(firstname = "Amit", lastname = "Shekhar")
        apiUserList.add(apiUserOne)

        val apiUserTwo = ApiUser(firstname = "Janishar", lastname = "Ali")
        apiUserList.add(apiUserTwo)

        val apiUserThree = ApiUser(firstname = "Anand", lastname = "Gaurav")
        apiUserList.add(apiUserThree)

        val apiUserFour = ApiUser(firstname = "Himanshu", lastname = "Sharma")
        apiUserList.add(apiUserFour)



        return apiUserList
    }


    fun getUserListWhoLovesCricket(): List<User> {

        val userList = ArrayList<User>()

        val userOne = User(id = 1, firstname = "Amit", lastname = "Shekhar")
        userList.add(userOne)

        val userTwo = User(id = 2, firstname = "Janishar", lastname = "Ali")
        userList.add(userTwo)

        val userThree = User(id= 5, firstname = "Himanshu", lastname = "Sharma")
        userList.add(userThree)

        val userFour = User(id= 6, firstname = "Anirudh", lastname = "Sharma")
        userList.add(userFour)

        return userList
    }


    fun getUserListWhoLovesFootball(): List<User> {

        val userList = ArrayList<User>()

        val userOne = User(id = 1, firstname = "Amit", lastname = "Shekhar")
        userList.add(userOne)

        val userTwo = User(id = 3, firstname = "Janishar", lastname = "Ali")
        userList.add(userTwo)

        val userThree = User(id = 5, firstname = "Himanshu", lastname = "Sharma")
        userList.add(userThree)

        val userFour = User(id = 6, firstname = "Anirudh", lastname = "Sharma")
        userList.add(userFour)


        return userList
    }

    fun convertApiUserListToUserList(apiUserList: List<ApiUser>): List<User> {

        val userList = ArrayList<User>()

        for (apiUser in apiUserList) {
            val user = User(apiUser.id, apiUser.firstname, apiUser.lastname)
            userList.add(user)
        }

        return userList
    }

    fun filterUserWhoLovesBoth(cricketFans: List<User>, footballFans: List<User>): List<User> {
        val userWhoLovesBoth = ArrayList<User>()

        for (footballFan in footballFans) {
            if (cricketFans.contains(footballFan)) {
                userWhoLovesBoth.add(footballFan)
            }
        }

        return userWhoLovesBoth
    }

    fun printSequentially(
        row1: List<String>?,
        row2: List<String>?,
        row3: List<String>?
    ): List<String>? {
        var finalList:ArrayList<String> = ArrayList<String>()
//        var finalList: ArrayList<String> = listOf<String>() as ArrayList<String>
        row1?.let {
            for (item in row1) {
                print(item + " ")
                finalList.add(item+" ")
            }
        }
        println()
        finalList.add("\n")
        row2?.let {
            for (item in row2) {
                print(item + " ")
                finalList.add(item+" ")
            }
        }
        println()
        finalList.add("\n")
        row3?.let {
            for (item in row3) {
                print(item + " ")
                finalList.add(item+" ")
            }
        }
        println()
        finalList.add("\n")
        return finalList.toList()
//        return emptyList()
    }

}
