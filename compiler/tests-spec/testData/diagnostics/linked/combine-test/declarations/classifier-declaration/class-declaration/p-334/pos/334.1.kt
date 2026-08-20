// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 334 -> sentence 334
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 334 -> sentence 334
 * NUMBER: 1
 * DESCRIPTION: precise type inference for an abstract class with an annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation
@MyAnnotation abstract class MyAbstractClass(val value: Int) {
    abstract fun compute(): Int
}

class MyConcrete(value: Int) : MyAbstractClass(value) {
    override fun compute() = value
}

fun case_1() {
    val instance = MyConcrete(42)
    instance checkType { check<MyConcrete>() }
    checkSubtype<MyConcrete>(instance)
}
