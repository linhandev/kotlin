// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 250 -> sentence 250
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 250 -> sentence 250
 *                inheritance, overriding -> paragraph 250 -> sentence 250
 *                inheritance, inheriting -> paragraph 250 -> sentence 250
 * NUMBER: 1
 * DESCRIPTION: precise types when overriding a generic interface producer narrows the return type covariantly
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
interface Factory<T> {
    fun create(): T
}

class IntFactory : Factory<Number> {
    override fun create(): Int = 1
}

fun case1() {
    val f = IntFactory()
    f checkType { check<IntFactory>() }
    checkSubtype<Factory<Number>>(f)
    f.create() checkType { check<Int>() }
    val asFactory: Factory<Number> = f
    asFactory.create() checkType { check<Number>() }
}

// TESTCASE NUMBER: 2
interface Source<T> {
    fun text(): T
}

class StringSource : Source<CharSequence> {
    override fun text(): String = "ok"
}

fun case2() {
    val s = StringSource()
    checkSubtype<Source<CharSequence>>(s)
    s.text() checkType { check<String>() }
    val asSource: Source<CharSequence> = s
    asSource.text() checkType { check<CharSequence>() }
}

// TESTCASE NUMBER: 3
interface Holder<T> {
    val current: T
}

class BoolHolder : Holder<Any> {
    override val current: Boolean = true
}

fun case3() {
    val h = BoolHolder()
    checkSubtype<Holder<Any>>(h)
    h.current checkType { check<Boolean>() }
    val asHolder: Holder<Any> = h
    asHolder.current checkType { check<Any>() }
}
