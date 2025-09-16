# NIT3213 Final – History
**Student:** MD Parvez Hussain (S8131988)

## Overview
Android app that:
1) Logs in using first name + student ID to retrieve a `keypass`.
2) Loads a dashboard list for topic **History**.
3) Opens a details screen with the full description.

Tech: Kotlin, ViewBinding, RecyclerView, Hilt (DI), Retrofit/OkHttp, Coroutines, JUnit tests.

## API
git clone [https://github.com/parvezhusseinshuvo-ui/nit3213-history.git]
- **POST** `/br/auth`
  - Body: `{"username":"MD Parvez Hussain","password":"8131988"}`
  - Sample success: `{"keypass":"history"}`
- **GET** `/dashboard/history`
  - Returns 7 history entities.

## Dependencies
-AndroidX UI
    -implementation("androidx.core:core-ktx:1.13.1")
    -implementation("androidx.appcompat:appcompat:1.7.0")
    -implementation("com.google.android.material:material:1.12.0")
    -implementation("androidx.recyclerview:recyclerview:1.3.2")
    -implementation("androidx.activity:activity-ktx:1.9.2")
    -implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.4")
    -implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")

-Networking (Retrofit/OkHttp)
    -implementation("com.squareup.retrofit2:retrofit:2.11.0")
    -implementation("com.squareup.retrofit2:converter-gson:2.11.0")
    -implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

-JSON
    -implementation("com.google.code.gson:gson:2.11.0")

-Coroutines
    -implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")

-Hilt (DI)
    -implementation("com.google.dagger:hilt-android:2.52")
    -kapt("com.google.dagger:hilt-compiler:2.52")

-Unit tests
    -testImplementation("junit:junit:4.13.2")
    -testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.8.1")

-Instrumented tests (optional)
    -androidTestImplementation("androidx.test.ext:junit:1.2.1")
    -androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")


## How to Run
- Android Studio (AGP 8.x), **JDK 17**.
- Open the project, select an emulator (API 34+), and Run the `app`.
- Launcher screen: **Login**.

## Login
Username: MD Parvez Hussain 
Password: 8131988

## How to Use
1. Launch the app — the **Login** screen appears.
2. Enter your credentials:
   - **Username:** MD Parvez Hussain
   - **Password:** 8131988
3. Tap **Login**. On success, the app fetches your topic key and loads the **History** data.
4. The **Dashboard** shows a list of history events in a RecyclerView.
5. Tap any event to open **Details**, where you’ll see the full description, years, location, and key figure.
> If the login is incorrect or there’s no internet, an error message will be shown.

## App Flow
- **Login** → calls `/br/auth` (campus `br`) → receives `keypass`.
- **Dashboard** → calls `/dashboard/{keypass}` → shows list of items.
- **Details** → shows event, years, location, key figure, and description.

## Tests
- `LoginViewModelTest` – verifies login emits `Success("history")`.
- `DashboardViewModelTest` – verifies list size and item names.

Run: Gradle → `:app:testDebugUnitTest` or right-click the test classes and Run.

## Screenshots
![Postman auth](screenshots/postman_auth.png)
![Postman dashboard](screenshots/postman_dashboard.png)
![Login](screenshots/app_login.png)
![Dashboard](screenshots/app_dashboard.png)
![Details](screenshots/app_details.png)

## Notes
- Internet permission enabled.
- Hilt entry point: `NIT3213App` (`@HiltAndroidApp`).
- Base URL in `NetworkModule.kt`: `https://nit3213api.onrender.com/`.

## Credits
-API provided by NIT3213 Assessment.
-Developed by Parvez (S8131988) for submission.
