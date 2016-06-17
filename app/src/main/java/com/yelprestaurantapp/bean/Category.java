package com.yelprestaurantapp.bean;

public class Category {

    private String name;
    private String alias;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public static class CategoryBuilder {

        private String name;
        private String alias;

        public CategoryBuilder name(String name) {
            this.name = name;
            return this;
        }
        public CategoryBuilder alias(String alias) {
            this.alias = alias;
            return this;
        }

        public Category build() {
            Category c = new Category();
            c.setName(this.name);
            c.setAlias(this.alias);
            return c;
        }

    }

}


