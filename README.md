<h1 align="center"> Room-Demo-Repo </h1>

A multi-module repo which is a successor of [RoomDemo](https://github.com/Pavneet-Sing/RoomDemo). This repo contains three individual modules, developed using latest trends.

To start developement from scratch, It is strongly recommended to follow:

[Making a Notes App Using Room Database](https://www.pluralsight.com/guides/making-a-notes-app-using-room-database): [Pavneet Singh](https://github.com/Pavneet-Sing).

Introduction
------------

The `room-demo-kotlin-mvvm-dagger` module is the most updated module and will be updated in near future.

It is written in kotlin and based on the MVVM architecture for clean code with Dagger 2, to implement unit testing.

It simplifies the code using andorid jetpack with ViewModel, Data-Binding, LiveData, Coroutines, Room and Lifecyclee though if you are a fan of RxAdnroid then can follow the `room-demo-rxandroid-livedata` module.


Structure
---------

This repo is divided into three independent modules which can be labelled as per complexity, based on underlying architecture and libs as:

#### [room-demo-kotlin-mvvm-dagger](room-demo-rxandroid-livedata): Advance

-  MVVM - Google recommended architecture to achieve smooth UI and background task management.
- [Android KTX][2] - Write concise code as kotlin help to reduce code length by 20 - 40%.
- [Architecture][3] - MVVM architecture with recommended practices and libs to implement UI, persistence and networking etc.
- [Data Binding][4] - To automatically handle UI and data handling(binding, updates etc) in XML via observer pattern.
- [Lifecycles][5] - Hassle-free UI updates to avoid memory leaks, crashes and smooth task execution flow.
- [LiveData][6] - Observer pattern based object to deliver data on UI(main) thread.
- [Room][7] - SQLite database with ORM.
- [ViewModel][8] - A logical entity to handle actions and background tasks with data persistence via viewmodelProviders and factory.
- [Kotlin Coroutines][9]: For background thread management and execution.
- [Dagger 2][10] For dependency injector.
- UI - Google material design lib with cardview and recyclerview.

#### [room-demo-rxandroid-livedata](room-demo-rxandroid-livedata): Intermediate

- [Java 8][2] - Write concise code with java-8 lambda and methods reference support.
- [RxAndroid][0] - To handle background task management and data transformation.
- [Lifecycles][5] - Hassle-free UI updates to avoid memory leaks, crashes and smooth task execution flow.
- [LiveData][6] - Observer pattern based object to deliver data on UI(main) thread.
- [Room][7] - SQLite database with ORM.
- UI - Google material design lib with cardview and recyclerview.

#### [room-demo-android-q](room-demo-rxandroid-livedata): Beginner

- [Java 8][2] - Write concise code with java-8 lambda and methods reference support.
- AsyncTask [Deprecated][1] - To handle background task management and data transformation.
- [Room][7] - SQLite database with ORM.
- UI - Google material design lib with cardview and recyclerview.

Note: This uses `allowMainThreadQueries()` for demonstration and ease, which either should be replaced with `AsyncTask` or by `RxJava` or `Coroutines`(view other modules).

Tools:
------

[Android Studio Stable Release ](https://developer.android.com/studio/)
Emulator - Android Q,R
Testing Device - Android Pie

[0]: https://github.com/ReactiveX/RxAndroid
[1]: https://android-review.googlesource.com/c/platform/frameworks/base/+/1156409/6/core/java/android/os/AsyncTask.java
[2]: https://developer.android.com/kotlin/ktx
[3]: https://developer.android.com/jetpack/arch/
[4]: https://developer.android.com/topic/libraries/data-binding/
[5]: https://developer.android.com/topic/libraries/architecture/lifecycle
[6]: https://developer.android.com/topic/libraries/architecture/livedata
[7]: https://developer.android.com/topic/libraries/architecture/room
[8]: https://developer.android.com/topic/libraries/architecture/viewmodel
[9]: https://kotlinlang.org/docs/reference/coroutines-overview.html
[10]: https://dagger.dev/users-guide

Contribution
------------

PRs are welcome though for major changes, raise an issue otherwise
1. Fork the repo
2. Create a new branch and commit your changes
3. Create a PR against `dev` branch.

License
-----------
<details>
<summary>Vew Apache License</summary>

```
Copyright 2019 Pavneet Singh.

Licensed to the Apache Software Foundation (ASF) under one or more contributor
license agreements.  See the NOTICE file distributed with this work for
additional information regarding copyright ownership.  The ASF licenses this
file to you under the Apache License, Version 2.0 (the "License"); you may not
use this file except in compliance with the License.  You may obtain a copy of
the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
License for the specific language governing permissions and limitations under
the License.
```
</details>