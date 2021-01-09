# Project Dwarf

Properties with a value and its value change listeners.

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
