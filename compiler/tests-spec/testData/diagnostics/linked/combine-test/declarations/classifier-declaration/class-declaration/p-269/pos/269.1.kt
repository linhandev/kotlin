// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 269 -> sentence 269
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 269 -> sentence 269
 * NUMBER: 1
 * DESCRIPTION: precise types when reading internal members of a public class from outside the class in the same module
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
class CodeHolder {
    internal val code = 42
}

fun case1() {
    val h = CodeHolder()
    h checkType { check<CodeHolder>() }
    h.code checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
class SignalBox {
    internal fun signal(): Int = 7
}

class SignalClient {
    fun ping(): Int = SignalBox().signal()
}

fun case2() {
    val b = SignalBox()
    b checkType { check<SignalBox>() }
    b.signal() checkType { check<Int>() }
    SignalClient().ping() checkType { check<Int>() }
}

// TESTCASE NUMBER: 3
class LabelBag(internal val label: String)

fun case3() {
    val bag = LabelBag("ok")
    bag checkType { check<LabelBag>() }
    bag.label checkType { check<String>() }
}
