package com.example.payal.adhikaar;

public class User {


        int Count;
        int Flag;
        String Name;
        public User()
        {

        }
        public User(int Count,int Flag,String Name)
        {
            this.Count=Count;
            this.Flag=Flag;
            this.Name=Name;

        }
        public int getCount()
        {
            return Count;
        }

        public int getFlag() {
            return Flag;
        }

        public String getName() {
            return Name;
        }

}
