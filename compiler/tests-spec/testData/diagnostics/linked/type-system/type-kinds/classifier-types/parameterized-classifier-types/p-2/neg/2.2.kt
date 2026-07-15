// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (NEGATIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 2 -> sentence 2
 * NUMBER: 2
 * DESCRIPTION: Parameterized types with wrong arity violate well-formedness conditions
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box6<T> {
    val value: T
}

class InvalidBox6 : Box6<String> {
    override val value: <!PROPERTY_TYPE_MISMATCH_ON_OVERRIDE!>Int<!> = 1
}


// TESTCASE NUMBER: 2
fun case_2() {
    checkSubtype<List<String>>(<!TYPE_MISMATCH!>listOf(1)<!>)
    checkSubtype<Map<Int, String>>(<!TYPE_MISMATCH!>mapOf(1 to 1)<!>)
}


// TESTCASE NUMBER: 3
interface Pair8<A, B> {
    fun first(): A
    fun second(): B
}

class InvalidPair8 : Pair8<String, Int> {
    override fun first(): String = "x"
    override fun second(): <!RETURN_TYPE_MISMATCH_ON_OVERRIDE!>String<!> = "x"
}


// TESTCASE NUMBER: 4
interface Container9<out T>

class OutContainer9 : Container9<String>

fun case_4(x: OutContainer9) {
    checkSubtype<Container9<Int>>(<!TYPE_MISMATCH!>x<!>)
}


// TESTCASE NUMBER: 5
interface Consumer10<in T>

class StringConsumer10 : Consumer10<String>

fun case_5(x: StringConsumer10) {
    checkSubtype<Consumer10<Int>>(<!TYPE_MISMATCH!>x<!>)
}
