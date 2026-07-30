// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 184 -> sentence 184
 * PRIMARY LINKS: inheritance, overriding -> paragraph 184 -> sentence 184
 *                type-system, subtyping, subtyping-rules -> paragraph 184 -> sentence 184
 *                inheritance, inheriting -> paragraph 184 -> sentence 184
 * NUMBER: 1
 * DESCRIPTION: type inference for covariant return types on override in a class declaration
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
open class Factory {
    open fun create(): Number = 1
}

class IntFactory : Factory() {
    override fun create(): Int = 2
}

fun case1() {
    val f = IntFactory()
    f checkType { check<IntFactory>() }
    checkSubtype<Factory>(f)
    f.create() checkType { check<Int>() }

    val asFactory: Factory = f
    asFactory.create() checkType { check<Number>() }
}

// TESTCASE NUMBER: 2
open class TextSource {
    open fun text(): CharSequence = "base"
}

class StringSource : TextSource() {
    override fun text(): String = "child"
}

fun case2() {
    val s = StringSource()
    s.text() checkType { check<String>() }
    val asSource: TextSource = s
    asSource.text() checkType { check<CharSequence>() }
}
