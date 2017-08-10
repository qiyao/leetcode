%.exe: %.cc
	g++ -std=c++11 -g $^ -o $@

all: add-two-numbers.exe longest-string.exe median-of-two-sorted-arrays.exe
