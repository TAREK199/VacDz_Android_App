package com.tarek.vaccins.model;

import java.util.List;

public class ChildrenResponse {



        public boolean success;
        public ChildrenData data;

        public class ChildrenData {
            public List<Children> enfants;
        }

}
