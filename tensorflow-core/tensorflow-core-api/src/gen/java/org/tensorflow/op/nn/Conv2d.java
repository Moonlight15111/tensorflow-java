/* Copyright 2018 The TensorFlow Authors. All Rights Reserved.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
=======================================================================*/

// This class has been generated, DO NOT EDIT!

package org.tensorflow.op.nn;

import java.util.List;
import org.tensorflow.Operand;
import org.tensorflow.Operation;
import org.tensorflow.OperationBuilder;
import org.tensorflow.Output;
import org.tensorflow.op.PrimitiveOp;
import org.tensorflow.op.Scope;
import org.tensorflow.op.annotation.Endpoint;
import org.tensorflow.op.annotation.Operator;
import org.tensorflow.types.family.TNumber;
import org.tensorflow.types.family.TType;

/**
 * Computes a 2-D convolution given 4-D `input` and `filter` tensors.
 * <p>
 * Given an input tensor of shape `[batch, in_height, in_width, in_channels]`
 * and a filter / kernel tensor of shape
 * `[filter_height, filter_width, in_channels, out_channels]`, this op
 * performs the following:
 * <p>
 * 1. Flattens the filter to a 2-D matrix with shape
 *    `[filter_height * filter_width * in_channels, output_channels]`.
 * 2. Extracts image patches from the input tensor to form a <i>virtual</i>
 *    tensor of shape `[batch, out_height, out_width,
 *    filter_height * filter_width * in_channels]`.
 * 3. For each patch, right-multiplies the filter matrix and the image patch
 *    vector.
 * <p>
 * In detail, with the default NHWC format,
 * <p>
 *     output[b, i, j, k] =
 *         sum_{di, dj, q} input[b, strides[1] * i + di, strides[2] * j + dj, q] *
 *                         filter[di, dj, q, k]
 * <p>
 * Must have `strides[0] = strides[3] = 1`.  For the most common case of the same
 * horizontal and vertices strides, `strides = [1, stride, stride, 1]`.
 * 
 * @param <T> data type for {@code output()} output
 */
@Operator(group = "nn")
public final class Conv2d<T extends TNumber> extends PrimitiveOp implements Operand<T> {
  
  /**
   * Optional attributes for {@link org.tensorflow.op.nn.Conv2d}
   */
  public static class Options {
    
    /**
     * @param useCudnnOnGpu 
     */
    public Options useCudnnOnGpu(Boolean useCudnnOnGpu) {
      this.useCudnnOnGpu = useCudnnOnGpu;
      return this;
    }
    
    /**
     * @param explicitPaddings If `padding` is `"EXPLICIT"`, the list of explicit padding amounts. For the ith
     * dimension, the amount of padding inserted before and after the dimension is
     * `explicit_paddings[2 * i]` and `explicit_paddings[2 * i + 1]`, respectively. If
     * `padding` is not `"EXPLICIT"`, `explicit_paddings` must be empty.
     */
    public Options explicitPaddings(List<Long> explicitPaddings) {
      this.explicitPaddings = explicitPaddings;
      return this;
    }
    
    /**
     * @param dataFormat Specify the data format of the input and output data. With the
     * default format "NHWC", the data is stored in the order of:
     *     [batch, height, width, channels].
     * Alternatively, the format could be "NCHW", the data storage order of:
     *     [batch, channels, height, width].
     */
    public Options dataFormat(String dataFormat) {
      this.dataFormat = dataFormat;
      return this;
    }
    
    /**
     * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
     * `input`. If set to k > 1, there will be k-1 skipped cells between each
     * filter element on that dimension. The dimension order is determined by the
     * value of `data_format`, see above for details. Dilations in the batch and
     * depth dimensions must be 1.
     */
    public Options dilations(List<Long> dilations) {
      this.dilations = dilations;
      return this;
    }
    
    private Boolean useCudnnOnGpu;
    private List<Long> explicitPaddings;
    private String dataFormat;
    private List<Long> dilations;
    
    private Options() {
    }
  }
  
  /**
   * Factory method to create a class wrapping a new Conv2d operation.
   * 
   * @param scope current scope
   * @param input A 4-D tensor. The dimension order is interpreted according to the value
   * of `data_format`, see below for details.
   * @param filter A 4-D tensor of shape
   * `[filter_height, filter_width, in_channels, out_channels]`
   * @param strides 1-D tensor of length 4.  The stride of the sliding window for each
   * dimension of `input`. The dimension order is determined by the value of
   * `data_format`, see below for details.
   * @param padding The type of padding algorithm to use.
   * @param options carries optional attributes values
   * @return a new instance of Conv2d
   */
  @Endpoint(describeByClass = true)
  public static <T extends TNumber> Conv2d<T> create(Scope scope, Operand<T> input, Operand<T> filter, List<Long> strides, String padding, Options... options) {
    OperationBuilder opBuilder = scope.env().opBuilder("Conv2D", scope.makeOpName("Conv2d"));
    opBuilder.addInput(input.asOutput());
    opBuilder.addInput(filter.asOutput());
    opBuilder = scope.applyControlDependencies(opBuilder);
    long[] stridesArray = new long[strides.size()];
    for (int i = 0; i < stridesArray.length; ++i) {
      stridesArray[i] = strides.get(i);
    }
    opBuilder.setAttr("strides", stridesArray);
    opBuilder.setAttr("padding", padding);
    if (options != null) {
      for (Options opts : options) {
        if (opts.useCudnnOnGpu != null) {
          opBuilder.setAttr("use_cudnn_on_gpu", opts.useCudnnOnGpu);
        }
        if (opts.explicitPaddings != null) {
          long[] explicitPaddingsArray = new long[opts.explicitPaddings.size()];
          for (int i = 0; i < explicitPaddingsArray.length; ++i) {
            explicitPaddingsArray[i] = opts.explicitPaddings.get(i);
          }
          opBuilder.setAttr("explicit_paddings", explicitPaddingsArray);
        }
        if (opts.dataFormat != null) {
          opBuilder.setAttr("data_format", opts.dataFormat);
        }
        if (opts.dilations != null) {
          long[] dilationsArray = new long[opts.dilations.size()];
          for (int i = 0; i < dilationsArray.length; ++i) {
            dilationsArray[i] = opts.dilations.get(i);
          }
          opBuilder.setAttr("dilations", dilationsArray);
        }
      }
    }
    return new Conv2d<T>(opBuilder.build());
  }
  
  /**
   * @param useCudnnOnGpu 
   */
  public static Options useCudnnOnGpu(Boolean useCudnnOnGpu) {
    return new Options().useCudnnOnGpu(useCudnnOnGpu);
  }
  
  /**
   * @param explicitPaddings If `padding` is `"EXPLICIT"`, the list of explicit padding amounts. For the ith
   * dimension, the amount of padding inserted before and after the dimension is
   * `explicit_paddings[2 * i]` and `explicit_paddings[2 * i + 1]`, respectively. If
   * `padding` is not `"EXPLICIT"`, `explicit_paddings` must be empty.
   */
  public static Options explicitPaddings(List<Long> explicitPaddings) {
    return new Options().explicitPaddings(explicitPaddings);
  }
  
  /**
   * @param dataFormat Specify the data format of the input and output data. With the
   * default format "NHWC", the data is stored in the order of:
   *     [batch, height, width, channels].
   * Alternatively, the format could be "NCHW", the data storage order of:
   *     [batch, channels, height, width].
   */
  public static Options dataFormat(String dataFormat) {
    return new Options().dataFormat(dataFormat);
  }
  
  /**
   * @param dilations 1-D tensor of length 4.  The dilation factor for each dimension of
   * `input`. If set to k > 1, there will be k-1 skipped cells between each
   * filter element on that dimension. The dimension order is determined by the
   * value of `data_format`, see above for details. Dilations in the batch and
   * depth dimensions must be 1.
   */
  public static Options dilations(List<Long> dilations) {
    return new Options().dilations(dilations);
  }
  
  /**
   * A 4-D tensor. The dimension order is determined by the value of
   * `data_format`, see below for details.
   */
  public Output<T> output() {
    return output;
  }
  
  @Override
  public Output<T> asOutput() {
    return output;
  }
  
  private Output<T> output;
  
  private Conv2d(Operation operation) {
    super(operation);
    int outputIdx = 0;
    output = operation.output(outputIdx++);
  }
}
