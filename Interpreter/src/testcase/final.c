// 使用if
int f1(int n) {
    if(n == 1) {
        return 1;
    } else {
        return n + f1(n - 1);
    }
}

/* 使用while */
int f2(int n) {
    int result = 0, i = n;
    while(i != 0) {
        result += i;
        i--;
    }
    return result;
}

/*
 * 使用for
 */
int f3(int n) {
    int result = 0;
    for(int i = 1; i <= n; i++) {
        result += n;
    }
    return result;
}

int main() {
    int result1 = f1(10);
    int result2 = f2(10);
    int result3 = f3(10);
    return 0;
}
