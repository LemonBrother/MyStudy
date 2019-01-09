cmake_minimum_required(VERSION 3.4.1)

add_library(take SHARED take.cpp )

find_library( log-lib log )


target_link_libraries( take   ${log-lib} )