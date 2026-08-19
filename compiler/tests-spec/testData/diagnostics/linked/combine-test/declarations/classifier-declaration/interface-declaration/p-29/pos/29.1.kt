// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -UNUSED_PARAMETER -UNUSED_VALUE -UNUSED_EXPRESSION
// SKIP_TXT
// JVM_TARGET: 1.8

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: combine-test, declarations, classifier-declaration, interface-declaration -> paragraph 29 -> sentence 29
 * PRIMARY LINKS: declarations, function-declaration -> paragraph 29 -> sentence 29
 *                type-system, type-kinds, flexible-types, platform-types -> paragraph 29 -> sentence 29
 * NUMBER: 1
 * DESCRIPTION: type inference when Kotlin class implements Java interface leaving default methods uncovered
 * HELPERS: checkType
 */

// FILE: JavaWithDefault.java
public interface JavaWithDefault {
    default int defaultValue() {
        return 1;
    }

    int abstractValue();
}

// FILE: 29.1.kt

// TESTCASE NUMBER: 1
class InheritJavaDefault : JavaWithDefault {
    override fun abstractValue(): Int = 10
}

fun case1() {
    val c = InheritJavaDefault()
    c checkType { check<InheritJavaDefault>() }
    checkSubtype<JavaWithDefault>(c)
    c.defaultValue() checkType { check<Int>() }
    c.abstractValue() checkType { check<Int>() }
}
