// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: declarations, property-declaration, read-only-property-declaration -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: inferred val, explicit val, custom getter, and getter-only computed val compile successfully
 */

// TESTCASE NUMBER: 1
val inferredVal = 1

// TESTCASE NUMBER: 2
val explicitVal: Int = 2

// TESTCASE NUMBER: 3
class ReadOnlyHolder {
    val withGetter: Int = 3
        get() = field + 1
}

// TESTCASE NUMBER: 4
val computedVal: Int
    get() = 4
