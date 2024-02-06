<h1>Simple Student Registration app in Jetpack compose Using Room Database</h1>
A simple basic student registration app that stores and retrieves ID, Name, Email, Phone number, and picture to a Room database.

<h2>Learning</h2>
<li>Basic few CRUD operations using room databse </li>
<li>Add a student with basic details, especially with a profile image.</li>
<li>Retrieve data from the database and Navigation System.</li>
<li>Uses Dagger Hilt for dependency injection to manage and provide app-level dependencies.</li>
<li>Easy to understand, manage, scalable code using the MVVM pattern.</li>

<h2>Screenshots</h2>
<p align="center">
  <img src="https://github.com/abdur-android-dev/Simple-Student-Registration-Android-Compose-Room-Database/blob/master/preview_1.png" alt="Screenshot 1" width="200"/>
  <img src="https://github.com/abdur-android-dev/Simple-Student-Registration-Android-Compose-Room-Database/blob/master/preview_2.png" alt="Screenshot 2" width="200"/>
  <img src="https://github.com/abdur-android-dev/Simple-Student-Registration-Android-Compose-Room-Database/blob/master/preview_3.png" alt="Screenshot 3" width="200"/>
</p>

<h2>Prerequisites</h2>
<li>Android Studio installed.</li>
<li>Understanding of MVVM architecture and Dagger Hilt.</li>

<h2>Getting Started</h2>
<li>Clone the repository:</li>
<pre><code></code>git clone https://github.com/abdur-android-dev/Simple-Student-Registration-Android-Compose-Room-Database.git</code></pre>
<li>Open the project in Android Studio.</li>
<li>Build and run the app on an Android emulator or device.</li>

<h2>Libraries Used</h2>
<pre><code></code>implementation 'androidx.navigation:navigation-compose:2.7.6'
    implementation "androidx.hilt:hilt-navigation-compose:1.1.0"

    def room_version = "2.6.1"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"

    def lifecycle_version = "2.7.0"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycle_version"

    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3'

    implementation "com.google.dagger:hilt-android:2.48"
    kapt "com.google.dagger:hilt-compiler:2.48"

    implementation 'androidx.constraintlayout:constraintlayout-compose:1.0.1'
    implementation 'io.coil-kt:coil-compose:2.5.0'</code></pre>

<h2>Licence</h2>
<code>This project is licensed under the Apache License, Version 2.0 - see the <a href="https://github.com/abdur-android-dev/Simple-Student-Registration-Android-Compose-Room-Database/blob/master/LICENSE.txt">LICENSE</a> file for details.</code>

<h3>Acknowledgments</h3>
<li>This is was a test project</li>
