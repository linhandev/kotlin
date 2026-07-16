// FIR_IDENTICAL
// DIAGNOSTICS: -UNUSED_VARIABLE -ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE -UNUSED_VALUE -UNUSED_PARAMETER -UNUSED_EXPRESSION
// SKIP_TXT

/*
 * KOTLIN DIAGNOSTICS SPEC TEST (POSITIVE)
 *
 * SPEC VERSION: 1.9-rfc+0.1
 * MAIN LINK: annotations, annotation-targets -> paragraph 1 -> sentence 1
 * NUMBER: 3
 * DESCRIPTION: value parameter and constructor annotation targets are valid
 */

// TESTCASE NUMBER: 1
@Target(AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.CONSTRUCTOR)
annotation class ParamCtor17303(val value: Int)

class ParamCtorClass17303 @ParamCtor17303(1) constructor(@ParamCtor17303(2) val x17303: Int)
