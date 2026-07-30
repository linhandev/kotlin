// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 288 -> sentence 288
 * PRIMARY LINKS: declarations, declaration-visibility -> paragraph 288 -> sentence 288
 *                declarations, classifier-declaration, object-declaration -> paragraph 288 -> sentence 288
 * NUMBER: 1
 * DESCRIPTION: precise types when an internal object is used within the same module without exposing it from public API signatures
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
internal object Token {
    val code: Int = 7
}

fun tokenCode(): Int = Token.code

fun case1() {
    tokenCode() checkType { check<Int>() }
    Token.code checkType { check<Int>() }
}

// TESTCASE NUMBER: 2
internal object Label {
    fun text(): String = "L"
}

fun case2() {
    Label.text() checkType { check<String>() }
}

// TESTCASE NUMBER: 3
internal object Flag {
    var on: Boolean = false
    fun flip(): Boolean {
        on = !on
        return on
    }
}

fun case3() {
    Flag.flip() checkType { check<Boolean>() }
    Flag.on checkType { check<Boolean>() }
}
