# If not stated otherwise in this file or this component's LICENSE file the
# following copyright and licenses apply:
#
# Copyright 2025 Sky
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

cmake_minimum_required(VERSION 3.12)
cmake_policy(VERSION 3.12)

project(DobbyInit LANGUAGES C CXX VERSION "3.13.0")

set(DOBBY_MAJOR_VERSION 3)
set(DOBBY_MINOR_VERSION 13)
set(DOBBY_MICRO_VERSION 0)

set(LEGACY_COMPONENTS ON)

add_subdirectory(git/plugins/EthanLog/client/lib)
add_subdirectory(git/daemon/init)


