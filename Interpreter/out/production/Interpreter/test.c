// 使用if
int add1(int n) {
    if(n == 1) {
        return 1;
    } else {
        return n + add1(n - 1);
    }
}

/* 使用while */
int add2(int n) {
    int result = 0;
    while(n != 0) {
        result += n;
        n--;
    }
    return result;
}

/*
 * 使用for
 */
int add3(int n) {
    int result = 0;
    for(int i = 1; i <= n; i++) {
        result += n;
    }
    return result;
}


int main() {
    int result1 = add1(10);
    int result2 = add2(10);
    int result3 = add3(10);
}

