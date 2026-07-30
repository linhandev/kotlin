// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 250 -> sentence 250
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 250 -> sentence 250
 *                inheritance, overriding -> paragraph 250 -> sentence 250
 *                inheritance, inheriting -> paragraph 250 -> sentence 250
 * NUMBER: 1
 * DESCRIPTION: overriding a generic interface producer may narrow the return type covariantly; contrasts with p-58/p-184 class-base covariance, p-219 dual non-generic interfaces, p-231 exact T match, and next-point incompatible returns
 */

// TESTCASE NUMBER: 1
interface Factory<T> {
    fun create(): T
}

class IntFactory : Factory<Number> {
    override fun create(): Int = 1
}

// TESTCASE NUMBER: 2
interface Source<T> {
    fun text(): T
}

class StringSource : Source<CharSequence> {
    override fun text(): String = "ok"
}

// TESTCASE NUMBER: 3
interface Holder<T> {
    val current: T
}

class BoolHolder : Holder<Any> {
    override val current: Boolean = true
}

fun box(): String {
    if (IntFactory().create() != 1) return "NOK: int-factory"
    val asFactory: Factory<Number> = IntFactory()
    val n: Number = asFactory.create()
    if (n != 1) return "NOK: via-factory"
    if (n !is Int) return "NOK: factory-is-int"
    if (IntFactory().create() !is Int) return "NOK: create-is-int"

    if (StringSource().text() != "ok") return "NOK: string-source"
    val asSource: Source<CharSequence> = StringSource()
    val cs: CharSequence = asSource.text()
    if (cs.toString() != "ok") return "NOK: via-source"
    if (cs !is String) return "NOK: text-is-string"

    if (BoolHolder().current != true) return "NOK: bool-holder"
    val asHolder: Holder<Any> = BoolHolder()
    val any: Any = asHolder.current
    if (any != true) return "NOK: via-holder"
    if (any !is Boolean) return "NOK: current-is-boolean"
    return "OK"
}
