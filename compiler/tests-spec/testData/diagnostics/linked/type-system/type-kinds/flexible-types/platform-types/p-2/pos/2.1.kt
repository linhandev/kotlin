// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, flexible-types, platform-types -> paragraph 2 -> sentence 2
 * NUMBER: 1
 * DESCRIPTION: Platform types from Java interop flexibilize to kotlin-compatible flexible types
 * HELPERS: checkType
 */

// FILE: Platform1.java

import org.jetbrains.annotations.*;

public class Platform1 {
    @NotNull
    public static Platform1 staticNN;
    @Nullable
    public static Platform1 staticN;
    public static Platform1 staticJ;
}

// FILE: cases.kt

// TESTCASE NUMBER: 1
fun case_1() {
    val nn = Platform1.staticNN
    checkSubtype<Platform1>(nn)
    checkSubtype<Platform1?>(nn)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val n = Platform1.staticN
    checkSubtype<Platform1?>(n)
    val y: Platform1? = n
}


// TESTCASE NUMBER: 3
fun case_3() {
    val j = Platform1.staticJ
    checkSubtype<Platform1>(j)
    checkSubtype<Platform1?>(j)
}


// TESTCASE NUMBER: 4
fun case_4(nn: Platform1, n: Platform1?, j: Platform1) {
    checkSubtype<Platform1>(nn)
    checkSubtype<Platform1?>(n)
    checkSubtype<Platform1>(j)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val nn = Platform1.staticNN
    val j: Platform1? = nn
    val any: Any? = Platform1.staticJ
    checkSubtype<Any?>(any)
}
