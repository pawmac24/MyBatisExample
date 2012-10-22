=== Instrukcja obsługi ===

1. mvn eclipse:clean eclipse:eclipse
2. Uruchomić serwer postgresql (zalecane) lub inny
3. Wgrac skrypt sqlowy dla odpowiedniego serwera z pliku:
	src\test\resources\org\mybatis\example\db\create_schema.sql
4. Uruchomić w Eclipsie (Run As/Debug As -> Java Application) plik:
	src\test\java\org\mybatis\example\ExampleApplication.java
