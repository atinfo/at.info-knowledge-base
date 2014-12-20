Generic DataProvider example: how to use Hibernate ORM and Java 8 tricks for retrieving / saving DB entities
======

Implemented via maven, testng, hibernate.

Main usage: DDT.

You can find detailed instructions here:

 - http://qa-automation-notes.blogspot.com/2014/11/ddt-generic-dataprovider-for-handling.html
 - http://qa-automation-notes.blogspot.com/2014/12/ddt-generic-dataprovider-update-to.html

Related discussion: http://automated-testing.info/t/generic-dataprovider-ili-kak-rabotat-s-db-sushhnostyami-v-usloviyah-masshtabirovaniya/5466

Inside of project you will see:

 - Generic DAO.
 - Hibernate and DP utils.
 - DB entities.
 - Sample tests for retrieving / saving data from / to multiple DB in parallel.

Tests look like the following:
```java
    @Entity(entity = Users.class, schema = AUTOMATION, ids = {1, 2})
    @Entity(entity = ProdUsers.class, schema = PRODUCTION, invocationCount = 5)
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
    public void getFirstSampleData(final Users user, final ProdUsers productionUser) {
        System.out.println(Thread.currentThread().getId() + " thread:\n" +
                "User = " + user + "\n" +
                "Production user = " + productionUser + "\n");
    }

    @Entity(entity = ProdUsers.class, schema = PRODUCTION, invocationCount = 1)
    @Entity(entity = Products.class, schema = PRODUCTION, invocationCount = 2, ids = {1})
    @Entity(entity = Products.class, schema = PRODUCTION, ids = {2})
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
    public void getSecondSampleData(final ProdUsers user, final Products firstProduct, final Products secondProduct) {
        System.out.println(Thread.currentThread().getId() + " thread:\n" +
                "User = " + user + "\n" +
                "First product = " + firstProduct + "\n" +
                "Second product = " + secondProduct + "\n");
    }

    @Entity(entity = ImportData.class, schema = AUTOMATION)
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
    public void getThirdSampleData(final ImportData importData) {
        System.out.println(Thread.currentThread().getId() + " thread:\n" + importData + "\n");
    }

    @Entity(entity = Users.class, schema = AUTOMATION, ids = {1})
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
    public void saveFirstSampleData(final Users user) {
        user.setEmail("test.user1@email.com");
        user.setPassword("password1");
        user.save();
    }

    @Entity(entity = Users.class, schema = AUTOMATION, ids = {1})
    @Test(dataProviderClass = DataProviderUtils.class, dataProvider = GENERIC_DP)
    public void saveSecondSampleData(final Users user) {
        user.setEmail("test.user3@email.com");
        user.save();
    }
```