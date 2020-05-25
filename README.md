# PowerAct

## Introduction

An Android library that can manipulate power-related actions with just few lines of code without root privilege.

(Use `AccessibilityService` and `DevicePolicyManager`)

## Usage

Download

[ ![Download](https://api.bintray.com/packages/ryuunoakaihitomi/maven/poweract/images/download.svg?version=1.0.1) ](https://bintray.com/ryuunoakaihitomi/maven/poweract/1.0.1/link)

```gradle
repositories {
    jcenter()
}

dependencies {
    implementation 'github.ryuunoakaihitomi.poweract:poweract:1.0.1'
}
    
```

Use

```java
// Lock screen, Without callback.
lockScreenBtn.setOnClickListener(v -> PowerAct.lockScreen(MainActivity.this));
// Show system power dialog, with callback.
powerDialogBtn.setOnClickListener(v -> PowerAct.showPowerDialog(MainActivity.this, callback));
// An additional widget.
PowerButton powerButton = findViewById(R.id.pwrBtn);
```

Create `res/values/poweract_config.xml`

```xml
<resources xmlns:tools="http://schemas.android.com/tools" tools:ignore="UnusedResources">
    <!--  In order to configure some UI properties you must rewrite the res of the library.  -->
    <string name="poweract_accessibility_service_label">Power Action Service</string>
    <string name="poweract_accessibility_service_description">The service is used to perform some power action without reaching the actual power button on the side of the phone. It will never collect any user data.</string>
    <string name="poweract_accessibility_service_summary">Virtual power key accessibility service.</string>
    <!--  Optional.  -->
    <bool name="poweract_accessibility_service_show_foreground_notification">true</bool>
</resources>
```

## Compatibility

API level|LockScreen|Show System Dialog
-|-|-
14 ~ 20 |√|×
21 ~ 27 |√|√
28+|√+|√

> √+ `Unlock by fingerprint`