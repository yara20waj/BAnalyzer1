package com.yarawajeeh.bmianalyzer.OOP;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Firebaseconnection {
    public static void addBMIRecord(BMIRecord records) {
        String key=DB.getCurrentUserBMIRecord().push().getKey();
        records.setId(key);
        DB.getCurrentUserBMIRecord().child(key).setValue(records);
    }
    public static void  addFood(Food foods){
        String key=DB.getCurrentUserBMIFood().push().getKey();
        foods.setId(key);
        DB.getCurrentUserBMIFood().child(key).setValue(foods);
    }
    public static void  removeFood(Food food){
        DB.getCurrentUserBMIFood().child(food.getId()).removeValue();
    }
    public static void removeBMIRecord(BMIRecord record){
        DB.getCurrentUserBMIRecord().child(record.getId()).removeValue();
    }
    public static class DB {
        public static DatabaseReference getUsers(){
            return FirebaseDatabase.getInstance().getReference("Users");
        }
        public static  DatabaseReference getCurrentUserData(){
            return getUsers().child(User.user.getmAuth().getCurrentUser().getUid());
        }
        public static DatabaseReference getCurrentUserBMIFood() {
            return getCurrentUserData().child("Food");
        }
        public static  DatabaseReference getCurrentUserFoods(){
            return getCurrentUserData().child("foods");
        }
        public static  DatabaseReference getCurrentUserBMIRecords(){
            return getCurrentUserData().child("records");
        }
        public static DatabaseReference getCurrentUserBMIRecord() {
            return getCurrentUserData().child("Record");
        }
        public static  DatabaseReference getCurrentUserName(){
            return getCurrentUserData().child("name");
        }
        public static  DatabaseReference getCurrentUserGender(){
            return getCurrentUserData().child("gender");
        }
        public static  DatabaseReference getCurrentUserBirthDate(){
            return getCurrentUserData().child("birthdate");
        }

    }
}
