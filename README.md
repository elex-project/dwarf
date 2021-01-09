# Project Dwarf

Properties with a value and its value change listeners.

## Gradle: Add Repository and Dependency

```kotlin
repositories {
	mavenCentral()
	maven {
		name = "Github Packages"
		url = uri("https://maven.pkg.github.com/elex-project/dwarf")
		credentials {
			username = project.findProperty("github.username") as String
			password = project.findProperty("github.token") as String
		}
	}
}
dependencies {
	implementation("com.elex-project:properties:2.0.2")
}
```

## Usage

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

```java
IntegerProperty integerProperty = new IntegerProperty(100);
ShortProperty shortProperty = new ShortProperty((short)100);

assertEquals(integerProperty, shortProperty.getValue());
```

```java
EnumProperty<MyEnum> enumProperty = new EnumProperty<>(MyEnum.ITEM_1);
enumProperty.addListener((PropertyListener<MyEnum>) (oldValue, newValue)
        -> log.info("{} -> {}", oldValue, newValue));
enumProperty.setValue(MyEnum.ITEM_2);
```

---
developed by Elex

https://www.elex-project.com
