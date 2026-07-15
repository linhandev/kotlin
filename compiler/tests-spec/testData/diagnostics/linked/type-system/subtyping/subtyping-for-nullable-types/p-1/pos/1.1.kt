// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, subtyping, subtyping-for-nullable-types -> paragraph 1 -> sentence 1
 * NUMBER: 1
 * DESCRIPTION: Nullable subtyping allows B and B? to substitute for A? when B <: A?
 * HELPERS: checkType
 */
// TESTCASE NUMBER: 1
fun <T> mk(): T = TODO()
class Foo1<A, B : A?> {
    val b: B = mk()
    val aQb: A? = b
}
fun case_1() {
    val f = Foo1<String, String?>()
    checkSubtype<String?>(f.aQb)
}

class Foo2<A, B : A?> {
    val bQ: B? = mk()
    val aQbQ: A? = bQ
}


// TESTCASE NUMBER: 2
fun case_2() {
    val f = Foo2<Number, Int>()
    checkSubtype<Number?>(f.aQbQ)
}

class Bar3<A, B : A> {
    val b: B = mk()
    val ab: A = b
}


// TESTCASE NUMBER: 3
fun case_3() {
    val b = Bar3<Number, Int>()
    checkSubtype<Number>(b.ab)
}


// TESTCASE NUMBER: 4
fun case_4() {
    val s: String? = null
    val a: Any? = s
    checkSubtype<Any?>(a)
}


// TESTCASE NUMBER: 5
fun case_5() {
    val i: Int? = 1
    val n: Number? = i
    checkSubtype<Number?>(n)
}
