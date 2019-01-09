cmake_minimum_required(VERSION 3.4.1)

add_library(              native-lib             SHARED             src/main/cpp/native-lib.cpp )


find_library( log-lib log )


target_link_libraries( native-lib   ${log-lib} )

include_directories(main/cpp/add main/cpp/take )

add_subdirectory(src/main/cpp/add)
add_subdirectory(src/main/cpp/take)