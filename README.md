# Project Dwarf

Properties with a value and its value change listeners.

## Add Repository
```kotlin
repositories {
	mavenCentral()
	maven {
		name = "Github Packages"
		url = uri("https://maven.pkg.github.com/elex-project/dwarf.properties")
		credentials {
			username = project.findProperty("github.username") as String
			password = project.findProperty("github.token") as String
		}
	}
}
```

## Sample code
```java
StringProperty property = new StringProperty();
PropertyListener<String> listener = new PropertyListener<String>() {
    @Override
    public void onValueChanged(final String oldValue, final String newValue) {
        log.info("{} -> {}", oldValue, newValue);
    }
};
property.addListener(listener);

property.setValue("Hello");
```

---
developed by Elex

https://www.elex-project.com
