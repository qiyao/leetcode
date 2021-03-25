%.exe: %.cc
	clang++ -std=c++11 -g $^ -o $@

EXES=add-two-numbers.exe longest-string.exe median-of-two-sorted-arrays.exe \
     count-of-smaller-numbers-after-self.exe coin-change.exe perfect-squares.exe \
     3sum.exe palindromic-substring.exe two-sum.exe

clean:
	rm -rf *.exe *.dSYM

all: $(EXES)
