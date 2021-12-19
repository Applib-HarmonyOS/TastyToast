# TastyToast

[![Build](https://github.com/applibgroup/TastyToast/actions/workflows/main.yml/badge.svg)](https://github.com/applibgroup/TastyToast/actions/workflows/main.yml)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=applibgroup_TastyToast&metric=alert_status)](https://sonarcloud.io/dashboard?id=applibgroup_TastyToast)

Make your native HMOS toast look beautiful.

## Preview
![gif](https://github.com/yadav-rahul/TastyToast/blob/lib/static/success.gif)
![gif](https://github.com/yadav-rahul/TastyToast/blob/lib/static/warning.gif)
![gif](https://github.com/yadav-rahul/TastyToast/blob/lib/static/error.gif)
![gif](https://github.com/yadav-rahul/TastyToast/blob/lib/static/info.gif)
![gif](https://github.com/yadav-rahul/TastyToast/blob/lib/static/default.gif)
![gif](https://github.com/yadav-rahul/TastyToast/blob/lib/static/confusion.gif)

# Source
This library has been inspired by [yadav-rahul/TastyToast](https://github.com/yadav-rahul/TastyToast).

## Integration

1. For using TastyToast module in sample app, include the source code and add the below dependencies in entry/build.gradle to generate hap/support.har.
```
 implementation project(path: ':tastytoast')
```
2. For using TastyToast module in separate application using har file, add the har file in the entry/libs folder and add the dependencies in entry/build.gradle file.
```
 implementation fileTree(dir: 'libs', include: ['*.har'])
```
3. For using TastyToast module from a remote repository in separate application, add the below dependencies in entry/build.gradle file.
```
implementation 'dev.applibgroup:TastyToast:1.0.0'
```

## Usage

### Java
```
TastyToast.makeText(getApplicationContext(), "Hello World !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
```
Last parameter here is the type of toast you want to show.


## License

* [Apache Version 2.0](http://www.apache.org/licenses/LICENSE-2.0.html)

```
Copyright 2016 Rahul Yadav

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

 http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
