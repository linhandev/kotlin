// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION -UNNECESSARY_LATEINIT
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 124 -> sentence 124
 * PRIMARY LINKS: declarations, classifier-declaration, classifier-initialization -> paragraph 124 -> sentence 124
 *                declarations, property-declaration, late-initialized-properties -> paragraph 124 -> sentence 124
 *                declarations, classifier-declaration, class-declaration, constructor-declaration -> paragraph 124 -> sentence 124
 * NUMBER: 1
 * DESCRIPTION: lateinit var assigned in init block has String getter result type in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class Host(val seed: String) {
    lateinit var name: String

    init {
        name = seed
    }

    fun get(): String = name
}

fun case1() {
    val viaOk = Host("ok")
    viaOk checkType { check<Host>() }
    viaOk.seed checkType { check<String>() }
    viaOk.get() checkType { check<String>() }
}

// TESTCASE NUMBER: 2
fun case2() {
    val viaHi = Host("hi")
    viaHi checkType { check<Host>() }
    viaHi.seed checkType { check<String>() }
    viaHi.get() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
fun case3() {
    val viaAlpha = Host("A")
    viaAlpha checkType { check<Host>() }
    viaAlpha.seed checkType { check<String>() }
    viaAlpha.get() checkType { check<String>() }
}
