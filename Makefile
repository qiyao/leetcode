%.exe: %.cc
	g++ -std=c++11 -g $^ -o $@

all: add-two-numbers.exe longest-string.exe median-of-two-sorted-arrays.exe count-of-smaller-numbers-after-self.exe coin-change.exe perfect-squares.exe
