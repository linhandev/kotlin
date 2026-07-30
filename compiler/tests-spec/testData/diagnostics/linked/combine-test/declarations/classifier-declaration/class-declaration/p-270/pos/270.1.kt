// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 270 -> sentence 270
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 270 -> sentence 270
 * NUMBER: 1
 * DESCRIPTION: precise types when reading explicit public members outside the declaring class
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class GateHolder {
    public val gate = 1
}

fun case1() {
    val h = GateHolder()
    h checkType { check<GateHolder>() }
    h.gate checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class OpenBox {
    public fun open(): Int = 2
}

class OpenClient {
    fun call(): Int = OpenBox().open()
}

fun case2() {
    val b = OpenBox()
    b checkType { check<OpenBox>() }
    b.open() checkType { check<Int>() }
    OpenClient().call() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
class TagBag(public val tag: String)

fun case3() {
    val bag = TagBag("ok")
    bag checkType { check<TagBag>() }
    bag.tag checkType { check<String>() }
}
