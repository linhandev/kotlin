// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, upper-and-lower-bounds -> paragraph 1 -> sentence 1
 * NUMBER: 2
 * DESCRIPTION: Multiple bounds define upper bounds intersection for type parameters
 * HELPERS: checkType
 */

interface A2
interface B2

// TESTCASE NUMBER: 1
fun <T> case_1(x: T) where T : A2, T : B2 {
    checkSubtype<A2>(x)
    checkSubtype<B2>(x)
}

// TESTCASE NUMBER: 2
class C2 : A2, B2

fun case_2(c: C2) {
    checkSubtype<A2>(c)
    checkSubtype<B2>(c)
}

// TESTCASE NUMBER: 3
fun <T> case_3(x: T): Any where T : Number, T : Comparable<T> {
    checkSubtype<Number>(x)
    checkSubtype<Comparable<T>>(x)
    return x
}

// TESTCASE NUMBER: 4
fun case_4(x: Int) {
    checkSubtype<Number>(x)
    checkSubtype<Comparable<Int>>(x)
}

// TESTCASE NUMBER: 5
open class Base5
open class Mid5 : Base5()
class Leaf5 : Mid5()

fun case_5(x: Leaf5): Base5 {
    checkSubtype<Base5>(x)
    return x
}
