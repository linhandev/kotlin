// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: type-system, type-kinds, classifier-types, parameterized-classifier-types -> paragraph 2 -> sentence 2
 * PRIMARY LINKS: type-system, subtyping, subtyping-rules -> paragraph 2 -> sentence 4
 * NUMBER: 2
 * DESCRIPTION: Nested and aliased parameterized classifier types are well-formed
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Box6<T> {
    val value: T
}

class IntBox6(override val value: Int) : Box6<Int>

fun case_1(x: IntBox6) {
    checkSubtype<Box6<Int>>(x)
}


// TESTCASE NUMBER: 2
fun case_2() {
    val list: List<String> = listOf("a")
    checkSubtype<List<String>>(list)
    val map: Map<Int, String> = mapOf(1 to "a")
    checkSubtype<Map<Int, String>>(map)
}


// TESTCASE NUMBER: 3
interface Pair8<A, B> {
    fun first(): A
    fun second(): B
}

class StringIntPair8 : Pair8<String, Int> {
    override fun first() = "x"
    override fun second() = 1
}

fun case_3(x: StringIntPair8) {
    checkSubtype<Pair8<String, Int>>(x)
}


// TESTCASE NUMBER: 4
interface Container9<out T>

class OutContainer9 : Container9<String>

fun case_4(x: OutContainer9) {
    checkSubtype<Container9<String>>(x)
    checkSubtype<Container9<Any>>(x)
}


// TESTCASE NUMBER: 5
interface Consumer10<in T>

class StringConsumer10 : Consumer10<String> {
    fun accept(value: String) {}
}

fun case_5(x: StringConsumer10) {
    checkSubtype<Consumer10<String>>(x)
}
