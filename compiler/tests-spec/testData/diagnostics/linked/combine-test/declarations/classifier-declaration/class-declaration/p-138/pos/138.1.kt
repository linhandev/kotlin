// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 138 -> sentence 138
 * PRIMARY LINKS: declarations, property-declaration -> paragraph 138 -> sentence 138
 *                declarations, property-declaration, getters-and-setters -> paragraph 138 -> sentence 138
 * NUMBER: 1
 * DESCRIPTION: instance val with custom getter and no backing field needs no initializer type inference in class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class RO(val x: Int) {
    val doubled: Int
        get() = x * 2
}

fun case1() {
    val ro = RO(3)
    ro checkType { check<RO>() }
    ro.x checkType { check<Int>() }
    ro.doubled checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class Named(val label: String) {
    val upper: String
        get() = label.uppercase()
}

fun case2() {
    val named = Named("ab")
    named checkType { check<Named>() }
    named.label checkType { check<String>() }
    named.upper checkType { check<String>() }
}

// TESTCASE NUMBER: 3
class Tracked(val seed: Int) {
    val log = mutableListOf<String>()
    val scaled: Int
        get() {
            log += "get"
            return seed * 3
        }

    init {
        log += "init"
    }
}

fun case3() {
    val tracked = Tracked(4)
    tracked checkType { check<Tracked>() }
    tracked.seed checkType { check<Int>() }
    tracked.log checkType { check<MutableList<String>>() }
    tracked.scaled checkType { check<Int>() }
}
