// WITH_STDLIB

/*
 * KOTLIN CODEGEN BOX SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 259 -> sentence 259
 * PRIMARY LINKS: declarations, declarations-with-type-parameters -> paragraph 259 -> sentence 259
 *                inheritance, inheriting -> paragraph 259 -> sentence 259
 * NUMBER: 1
 * DESCRIPTION: generic interface implementations share one runtime classifier across type arguments; distinct subclasses remain distinct; typed use-sites keep member access
 */

// TESTCASE NUMBER: 1
interface Box<T>

class BoxImpl<T> : Box<T>

// TESTCASE NUMBER: 2
interface Holder<T> {
    val current: T
}

class HolderImpl<T>(override val current: T) : Holder<T>

// TESTCASE NUMBER: 3
interface Factory<T> {
    fun create(): T
}

class FactoryImpl<T>(private val value: T) : Factory<T> {
    override fun create(): T = value
}

fun box(): String {
    // Same generic class: runtime classifier is independent of type arguments
    if (BoxImpl<Int>()::class != BoxImpl<String>()::class) return "NOK: box-kclass"
    val asBoxInt: Box<Int> = BoxImpl()
    val asBoxString: Box<String> = BoxImpl()
    if (asBoxInt::class != asBoxString::class) return "NOK: via-box"
    if (!BoxImpl::class.isInstance(asBoxInt)) return "NOK: box isInstance Int"
    if (!BoxImpl::class.isInstance(asBoxString)) return "NOK: box isInstance String"
    if (asBoxInt !is BoxImpl<*>) return "NOK: is-check BoxImpl"

    // Typed interface refs still expose members according to static type arguments
    if (HolderImpl(1)::class != HolderImpl("x")::class) return "NOK: holder-kclass"
    val asHolderInt: Holder<Int> = HolderImpl(1)
    val asHolderString: Holder<String> = HolderImpl("x")
    if (asHolderInt::class != asHolderString::class) return "NOK: via-holder"
    if (asHolderInt.current != 1) return "NOK: holder Int value"
    if (asHolderString.current != "x") return "NOK: holder String value"

    if (FactoryImpl(true)::class != FactoryImpl(0)::class) return "NOK: factory-kclass"
    val asFactory: Factory<Boolean> = FactoryImpl(true)
    if (asFactory.create() != true) return "NOK: factory value"
    if (!FactoryImpl::class.isInstance(asFactory)) return "NOK: factory isInstance"

    // Distinct concrete subclasses keep distinct classifiers (not the same erasure case)
    class BI : Box<Int>
    class BS : Box<String>
    if (BI()::class == BS()::class) return "NOK: distinct-classes-should-differ"
    if (BI()::class == BoxImpl::class) return "NOK: BI must differ from BoxImpl"
    return "OK"
}
