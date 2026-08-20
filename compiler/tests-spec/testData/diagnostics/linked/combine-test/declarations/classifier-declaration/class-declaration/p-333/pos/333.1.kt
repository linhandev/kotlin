// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION

// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, class-declaration -> paragraph 333 -> sentence 333
 * PRIMARY LINKS: annotations, annotation-declarations -> paragraph 333 -> sentence 333
 * NUMBER: 1
 * DESCRIPTION: precise type inference for an interface with an annotation
 * HELPERS: checkType
 */

// TESTCASE NUMBER: 1
annotation class MyAnnotation
@MyAnnotation interface MyInterface {
    fun compute(): Int
}

class MyImpl(val value: Int) : MyInterface {
    override fun compute() = value
}

fun case_1() {
    val instance: MyInterface = MyImpl(42)
    instance checkType { check<MyInterface>() }
    checkSubtype<MyInterface>(instance)
}
